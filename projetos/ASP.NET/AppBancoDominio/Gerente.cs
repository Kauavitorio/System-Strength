using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace AppBancoDominio
{
    public class Gerente
    {
        [DisplayName("Nome do Gerente:")]
        public string nm_ger { get; set; }
        [DisplayName("CPF do Gerente:")]
        [StringLength(14, MinimumLength = 14, ErrorMessage = "Esse campo deve ter 11 caracteres!")]
        public string cpf_ger { get; set; }
        [DisplayName("Senha de Gerente:")]
        [Required(ErrorMessage = "Obrigatório digitar a Senha de Gerente!")]
        public string senha_ger { get; set; }
    }
}
