using AppBancoADO;
using AppBancoDominio;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Linq;


namespace AppBancoDLL
{
    public class VeterinarioDAO
    {
        private Banco db;
        public void Insert(Veterinario veterinario)
        {
            string strQuery = string.Format("Insert into tbl_veterinario(nm_vet, tel_vet, crmv_vet, cpf_ger)" +
                    "values('{0}', '{1}','{2}');", veterinario.nm_vet, veterinario.tel_vet, veterinario.crmv_vet, veterinario.cpf_vet.Replace(".", string.Empty).Replace("-", string.Empty));
            using (db = new Banco())
            {
                db.ExecutaComando(strQuery);
            }
        }
        public void Atualizar(Veterinario veterinario)
        {
            var stratualiza = "";
            stratualiza += "update tbl_veterinario set ";
            stratualiza += string.Format(" nm_vet = '{0}', ", veterinario.nm_vet);
            stratualiza += string.Format(" tel_vet = '{0}', ", veterinario.tel_vet);
            stratualiza += string.Format(" crmv_vet = '{0}', ", veterinario.crmv_vet);
            stratualiza += string.Format(" cpf_vet = '{0}' ", veterinario.cpf_vet.ToString().Replace(".", string.Empty).Replace("-", string.Empty));
            stratualiza += string.Format(" Where id_vet = {0};", veterinario.id_vet);

            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }

        public void Excluir(Veterinario veterinario)
        {
            var stratualiza = "";
            stratualiza += "delete from tbl_veterinario";
            stratualiza += string.Format(" Where id_ger = {0};", veterinario.id_vet);
            using (db = new Banco())
            {
                db.ExecutaComando(stratualiza);
            }
        }
        public void Salvar(Veterinario veterinario)
        {
            if (veterinario.id_vet > 0)
            {
                Atualizar(veterinario);
            }
            else
            {
                Insert(veterinario);
            }
        }
        public List<Veterinario> Listar()
        {
            var db = new Banco();
            var strQuery = "select * from tbl_veterinario";
            var retorno = db.retornaComando(strQuery);
            return listaVeterinario(retorno);
        }
        public Veterinario ListarId(int id)
        {
            using (db = new Banco())
            {
                var strQuery = string.Format("select * from tbl_veterinario where id_vet = {0} ", id);
                var retorno = db.retornaComando(strQuery);
                return listaVeterinario(retorno).FirstOrDefault();
            }
        }
        private List<Veterinario> listaVeterinario(MySqlDataReader retorno)
        {
            var veterinarios = new List<Veterinario>();
            while (retorno.Read())
            {
                var TempVeterinario = new Veterinario()
                {
                    id_vet = int.Parse(retorno["id_vet"].ToString()),
                    nm_vet = retorno["nm_vet"].ToString(),
                    tel_vet = retorno["tel_vet"].ToString(),
                    crmv_vet = int.Parse(retorno["crmv_vet"].ToString()),
                    cpf_vet = retorno["cpf"].ToString().Replace(".", string.Empty).Replace("-", string.Empty),
                };
                veterinarios.Add(TempVeterinario);
            }
            return veterinarios;
        }
    }
}
