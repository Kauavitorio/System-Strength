using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AppBancoDLL
{
    public class ConsultaDAO
    {
        private Banco db;
        public void Insert(Consulta consulta)
        {
            string strQuery = string.Format("Insert into tbl_consulta(raca_animal, nm_cli, cpf_cli, end_cli, tel_cli, nm_vet, dt_consulta, hr_consulta, forma_paga)" +
                    "values('{0}', '{1}', '{2}', '{3}', '{4}', '{5}', '{6}', '{7}', '{8}');", consulta.raca_animal, consulta.nm_cli, consulta.cpf_cli.Replace(".", string.Empty).Replace("-", string.Empty), consulta.end_cli, consulta.tel_cli, consulta.nm_vet, consulta.dt_consulta.ToString("yyyy-MM-dd"), consulta.hr_consulta.ToString("HH:mm"), consulta.forma_paga);
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Consulta consulta)
        {
            var stratualiza = "";
            stratualiza += "update tbl_consulta set ";
            stratualiza += string.Format(" raca_animal = '{0}', ", consulta.raca_animal);
            stratualiza += string.Format(" nm_cli = '{0}', ", consulta.nm_cli);
            stratualiza += string.Format(" cpf_cli = '{0}', ", consulta.cpf_cli.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" end_cli = '{0}', ", consulta.end_cli);
            stratualiza += string.Format(" tel_cli = '{0}', ", consulta.tel_cli);
            stratualiza += string.Format(" nm_vet = '{0}', ", consulta.nm_vet);
            stratualiza += string.Format(" dt_consulta = '{0}', ", consulta.dt_consulta.ToString("yyyy-MM-dd"));
            stratualiza += string.Format(" hr_consulta = '{0}', ", consulta.hr_consulta.ToString("HH:mm"));
            stratualiza += string.Format(" forma_paga = '{0}' ", consulta.forma_paga);
            stratualiza += string.Format(" Where cd_animal = {0};", consulta.cd_animal);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Consulta consulta)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_consulta";
            stratualiza += string.Format(" Where cd_animal = {0};", consulta.cd_animal);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Consulta consulta)
        {
            if (consulta.cd_animal > 0)
            {
                Atualizar(consulta);
            }
            else
            {
                Insert(consulta);
            }
        }
        public List<Consulta> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_consulta";
            var retorno = db.retornaComando(strQuery);
            return listaConsulta(retorno);
        }
        public Consulta ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_consulta where cd_animal = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaConsulta(retorno).FirstOrDefault();
            }
        }
        private List<Consulta> listaConsulta(MySqlDataReader retorno)
        {
            var consultas = new List<Consulta>();
            while (retorno.Read())
            {
                var TempConsulta = new Consulta()
                {
                    cd_animal = int.Parse(retorno["cd_animal"].ToString()),
                    raca_animal = retorno["raca_animal"].ToString(),
                    nm_cli = retorno["nm_cli"].ToString(),
                    cpf_cli = retorno["cpf_cli"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                    end_cli = retorno["end_cli"].ToString(),
                    tel_cli = retorno["tel_cli"].ToString(),
                    nm_vet = retorno["nm_vet"].ToString(),
                    dt_consulta = DateTime.Parse(retorno["dt_consulta"].ToString()),
                    hr_consulta = DateTime.Parse(retorno["hr_consulta"].ToString()),
                    forma_paga = retorno["forma_paga"].ToString(),
                };
                consultas.Add(TempConsulta);
            }
            return consultas;
        }
    }
}
