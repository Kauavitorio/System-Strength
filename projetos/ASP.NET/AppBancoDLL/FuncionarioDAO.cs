using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;


namespace AppBancoDLL
{
    public class FuncionarioDAO
    {
        private Banco db;
        public void Insert(Funcionario funcionario)
        {
            string strQuery = string.Format("Insert into tbl_func(nm_func, cg_func, end_func, tel_func, cpf_func)" +
                    "values('{0}', '{1}','{2}', '{3}', '{4}');", funcionario.nm_func, funcionario.cg_func, funcionario.end_func, funcionario.tel_func, funcionario.cpf_func.Replace(".", string.Empty).Replace("-", string.Empty)); ;            
            
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Funcionario funcionario)
        {
            var stratualiza = "";
            stratualiza += "update tbl_func set ";
            stratualiza += string.Format(" nm_func = '{0}', ", funcionario.nm_func);
            stratualiza += string.Format(" cg_func = '{0}', ", funcionario.cg_func);
            stratualiza += string.Format(" end_func = '{0}', ", funcionario.end_func);
            stratualiza += string.Format(" tel_func = '{0}', ", funcionario.tel_func);
            stratualiza += string.Format(" cpf_func = '{0}' ", funcionario.cpf_func.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" Where id_func = {0};", funcionario.id_func);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Funcionario funcionario)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_func";
            stratualiza += string.Format(" Where id_func = {0};", funcionario.id_func);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Funcionario funcionario)
        {
            if (funcionario.id_func > 0)
            {
                Atualizar(funcionario);
            }
            else
            {
                Insert(funcionario);
            }
        }
        public List<Funcionario> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_func";
            var retorno = db.retornaComando(strQuery);
            return listaFuncionario(retorno);
        }
        public Funcionario ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_func where id_func = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaFuncionario(retorno).FirstOrDefault();
            }
        }
        private List<Funcionario> listaFuncionario(MySqlDataReader retorno)
        {
            var funcionarios = new List<Funcionario>();
            while (retorno.Read())
            {
                var TempFuncionario = new Funcionario()
                {
                    id_func = int.Parse(retorno["id_func"].ToString()),
                    nm_func = retorno["nm_func"].ToString(),
                    cg_func = retorno["cg_func"].ToString(),
                    end_func = retorno["end_func"].ToString(),
                    tel_func = retorno["tel_func"].ToString(),
                    cpf_func = retorno["cpf_func"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                };
                funcionarios.Add(TempFuncionario);
            }
            return funcionarios;
        }
    }
}