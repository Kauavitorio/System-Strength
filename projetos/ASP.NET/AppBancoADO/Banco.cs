using System;
using MySql.Data.MySqlClient;
using System.Configuration;

namespace AppBancoADO
{
    public class Banco : IDisposable
    {
        private readonly MySqlConnection conexao;
        public Banco()
        {
            conexao = new MySqlConnection(ConfigurationManager.ConnectionStrings["conexao"].ConnectionString);
            conexao.Open();
        }
        public void ExecutaComando(string strQuery)
        {
            MySqlCommand vComando = new MySqlCommand
            {
                CommandText = strQuery,
                CommandType = System.Data.CommandType.Text,
                Connection = conexao
            };
            vComando.ExecuteNonQuery();
        }
        public MySqlDataReader retornaComando(string strQuery)
        {
            var vComando = new MySqlCommand(strQuery, conexao);
            return vComando.ExecuteReader();
        }
        public void Dispose()
        {
            if (conexao.State == System.Data.ConnectionState.Open)
                conexao.Close();
        }
    }
}