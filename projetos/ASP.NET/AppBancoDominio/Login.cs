using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace AppBancoDominio
{
    public class Login
    {

        [DisplayName("Nome de Usúario:")]
        [Required(ErrorMessage = "Obrigatório digitar o Nome de Usuário!")]
        public string user_login { get; set; }

        [DisplayName("Senha de Usúario:")]
        [Required(ErrorMessage = "Obrigatório digitar a Senha!")]
        public string senha_login { get; set; }
    }
}
