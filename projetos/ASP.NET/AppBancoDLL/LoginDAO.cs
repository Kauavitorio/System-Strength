using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppBancoDLL
{
    public class LoginDAO
    {
        private Banco db;
        public void Insert(Login login)
        {
            string strQuery = string.Format("Insert into tbl_login(user_login, senha_login)" +
                    "values('{0}', '{1}');", login.user_login, login.senha_login); ;
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Login login)
        {
            var stratualiza = "";
            stratualiza += "update tbl_login set ";
            stratualiza += string.Format(" user_login = '{0}', ", login.user_login);
            stratualiza += string.Format(" senha_login = '{1}', ", login.senha_login);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Login login)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_login";
            stratualiza += string.Format(" Where senha_login = {0};", login.senha_login);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Login login)
        {
            if (login.senha_login != "")
            {
                Atualizar(login);
            }
            else
            {
                Insert(login);
            }
        }
        public List<Login> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_login";
            var retorno = db.retornaComando(strQuery);
            return listaLogin(retorno);
        }
        public Login ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_login where senha_login = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaLogin(retorno).FirstOrDefault();
            }
        }
        private List<Login> listaLogin(MySqlDataReader retorno)
        {
            var logins = new List<Login>();
            while (retorno.Read())
            {
                var TempLogin = new Login()
                {
                    user_login = retorno["user_login"].ToString(),
                    senha_login = retorno["senha_login"].ToString(),
                };
                logins.Add(TempLogin);
            }
            return logins;
        }
    }
}
