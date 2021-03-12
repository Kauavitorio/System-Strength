using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AppBancoDLL
{
    public class BanhoTosaDAO
    {
        private Banco db;
        public void Insert(BanhoTosa banhotosa)
        {
            string strQuery = string.Format("Insert into tbl_BanhoTosa(raca_animal, nm_cli, cpf_cli, end_cli, tel_cli, nm_func, dt_serv, hr_serv, forma_paga)" +
                    "values('{0}', '{1}', '{2}', '{3}', '{4}', '{5}', '{6}', '{7}', '{8}');", banhotosa.raca_animal, banhotosa.nm_cli, banhotosa.cpf_cli.Replace(".", string.Empty).Replace("-", string.Empty), banhotosa.end_cli, banhotosa.tel_cli, banhotosa.nm_func, banhotosa.dt_serv.ToString("yyyy-MM-dd"), banhotosa.hr_serv.ToString("HH:mm"), banhotosa.forma_paga);
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(BanhoTosa banhotosa)
        {
            var stratualiza = "";
            stratualiza += "update tbl_BanhoTosa set ";
            stratualiza += string.Format(" raca_animal = '{0}', ", banhotosa.raca_animal);
            stratualiza += string.Format(" nm_cli = '{0}', ", banhotosa.nm_cli);
            stratualiza += string.Format(" cpf_cli = '{0}', ", banhotosa.cpf_cli.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" end_cli = '{0}', ", banhotosa.end_cli);
            stratualiza += string.Format(" tel_cli = '{0}', ", banhotosa.tel_cli);
            stratualiza += string.Format(" nm_func = '{0}', ", banhotosa.nm_func);
            stratualiza += string.Format(" dt_serv = '{0}', ", banhotosa.dt_serv.ToString("yyyy-MM-dd"));
            stratualiza += string.Format(" hr_serv = '{0}', ", banhotosa.hr_serv.ToString("HH:mm"));
            stratualiza += string.Format(" forma_paga = '{0}' ", banhotosa.forma_paga);
            stratualiza += string.Format(" Where cd_Animal = {0};", banhotosa.cd_Animal);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(BanhoTosa banhotosa)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_BanhoTosa";
            stratualiza += string.Format(" Where cd_animal = {0};", banhotosa.cd_Animal);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(BanhoTosa banhotosa)
        {
            if (banhotosa.cd_Animal > 0)
            {
                Atualizar(banhotosa);
            }
            else
            {
                Insert(banhotosa);
            }
        }
        public List<BanhoTosa> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_BanhoTosa";
            var retorno = db.retornaComando(strQuery);
            return listaBanhoTosa(retorno);
        }
        public BanhoTosa ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_BanhoTosa where cd_Animal = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaBanhoTosa(retorno).FirstOrDefault();
            }
        }
        private List<BanhoTosa> listaBanhoTosa(MySqlDataReader retorno)
        {
            var banhotosas = new List<BanhoTosa>();
            while (retorno.Read())
            {
                var TempBanhoTosa = new BanhoTosa()
                {
                    cd_Animal = int.Parse(retorno["cd_Animal"].ToString()),
                    raca_animal = retorno["raca_animal"].ToString(),
                    nm_cli = retorno["nm_cli"].ToString(),
                    cpf_cli = retorno["cpf_cli"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                    end_cli = retorno["end_cli"].ToString(),
                    tel_cli = retorno["tel_cli"].ToString(),
                    nm_func = retorno["nm_func"].ToString(),
                    dt_serv = DateTime.Parse(retorno["dt_serv"].ToString()),
                    hr_serv = DateTime.Parse(retorno["hr_serv"].ToString()),
                    forma_paga = retorno["forma_paga"].ToString(),
                };
                banhotosas.Add(TempBanhoTosa);
            }
            return banhotosas;
        }
    }
}