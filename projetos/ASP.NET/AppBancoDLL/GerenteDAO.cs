using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Linq;


namespace AppBancoDLL
{
    public class GerenteDAO
    {
        private Banco db;
        public void Insert(Gerente gerente)
        {
            string strQuery = string.Format("Insert into tbl_gerente(nm_ger, cpf_ger, senha_ger)" +
                    "values('{0}', '{1}','{2}');", gerente.nm_ger, gerente.cpf_ger.Replace(".", string.Empty).Replace("-", string.Empty), gerente.senha_ger); ;
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Gerente gerente)
        {
            var stratualiza = "";
            stratualiza += "update tbl_gerente set ";
            stratualiza += string.Format(" nm_ger = '{0}', ", gerente.nm_ger);
            stratualiza += string.Format(" cpf_ger = '{0}' ", gerente.cpf_ger.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" senha_ger = '{0}', ", gerente.senha_ger);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Gerente gerente)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_gerente";
            stratualiza += string.Format(" Where senha_ger = {0};", gerente.senha_ger);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Gerente gerente)
        {
            if (gerente.senha_ger != "")
            {
                Atualizar(gerente);
            }
            else
            {
                Insert(gerente);
            }
        }
        public List<Gerente> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_gerente";
            var retorno = db.retornaComando(strQuery);
            return listaGerente(retorno);
        }
        public Gerente ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_gerente where senha_ger = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaGerente(retorno).FirstOrDefault();
            }
        }
        private List<Gerente> listaGerente(MySqlDataReader retorno)
        {
            var gerentes = new List<Gerente>();
            while (retorno.Read())
            {
                var TempGerente = new Gerente()
                {
                    nm_ger = retorno["nm_ger"].ToString(),
                    cpf_ger = retorno["cpf"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                    senha_ger = retorno["id_ger"].ToString(),
                };
                gerentes.Add(TempGerente);
            }
            return gerentes;
        }
    }
}