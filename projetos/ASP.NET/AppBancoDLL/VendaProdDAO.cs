using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AppBancoDLL
{
    public class VendaProdDAO
    {
        private Banco db;
        public void Insert(VendaProd vendaprod)
        {
            string strQuery = string.Format("Insert into tbl_VendaProd(Nm_cli, Cpf_cli, End_cli, Comple_cli, Tel_cli, nm_prod, Qntd_prod, Cat_prod, Forma_paga)" +
                    "values('{0}', '{1}', '{2}', '{3}', '{4}', '{5}', '{6}', '{7}', '{8}');", vendaprod.Nm_cli, vendaprod.Cpf_cli.Replace(".", string.Empty).Replace("-", string.Empty), vendaprod.End_cli, vendaprod.Comple_cli, vendaprod.Tel_cli,vendaprod.Nm_prod, vendaprod.Qntd_prod ,vendaprod.Cat_prod, vendaprod.Forma_paga);
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
    }
}
