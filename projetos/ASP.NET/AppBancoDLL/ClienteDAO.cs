using AppBancoADO;
using System.Collections.Generic;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System.Linq;

namespace AppBancoDLL
{
    public class ClienteDAO
    {
        private Banco db;
        public void Insert(Cliente cliente)
        {
            string strQuery = string.Format("Insert into tbl_cliente(nm_cli, end_cli, comple_cli, tel_cli, cpf_cli)" +
                    "values('{0}', '{1}','{2}', '{3}', '{4}');", cliente.nm_cli, cliente.end_cli, cliente.comple_cli, cliente.tel_cli, cliente.cpf_cli.Replace(".", string.Empty).Replace("-", string.Empty)); ;
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Cliente cliente)
        {
            var stratualiza = "";
            stratualiza += "update tbl_cliente set ";
            stratualiza += string.Format(" nm_cli = '{0}', ", cliente.nm_cli);
            stratualiza += string.Format(" end_cli = '{0}', ", cliente.end_cli);
            stratualiza += string.Format(" comple_cli = '{0}', ", cliente.comple_cli);
            stratualiza += string.Format(" tel_cli = '{0}', ", cliente.tel_cli);
            stratualiza += string.Format(" cpf_cli = '{0}' ", cliente.cpf_cli.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" Where id_cli = {0};", cliente.id_cli);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Cliente cliente)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_cliente";
            stratualiza += string.Format(" Where id_cli = {0};", cliente.id_cli);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Cliente cliente)
        {
            if (cliente.id_cli > 0)
            {
                Atualizar(cliente);
            }
            else
            {
                Insert(cliente);
            }
        }
        public List<Cliente> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_cliente";
            var retorno = db.retornaComando(strQuery);
            return listaCliente(retorno);
        }
        public Cliente ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_cliente where id_cli = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaCliente(retorno).FirstOrDefault();
            }
        }
        private List<Cliente> listaCliente(MySqlDataReader retorno)
        {
            var clientes = new List<Cliente>();
            while (retorno.Read())
            {
                var TempCliente = new Cliente()
                {
                    id_cli = int.Parse(retorno["id_cli"].ToString()),
                    nm_cli = retorno["nm_cli"].ToString(),
                    end_cli = retorno["end_cli"].ToString(),
                    comple_cli = retorno["comple_cli"].ToString(),
                    tel_cli = retorno["tel_cli"].ToString(),
                    cpf_cli = retorno["cpf_cli"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                };
                clientes.Add(TempCliente);
            }
            return clientes;
        }
    }
}