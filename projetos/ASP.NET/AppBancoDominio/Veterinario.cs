using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace AppBancoDominio
{
    public class Veterinario
    {
        [DisplayName("ID do Veterinário:")]
        public int id_vet { get; set; }
        [DisplayName("CPF do Veterinário:")]
        [StringLength(14, MinimumLength = 14, ErrorMessage = "Esse campo deve ter 11 caracteres!")]
        public string cpf_vet { get; set; }
        [DisplayName("Número de CRMV do Veterinário:")]
        public int crmv_vet { get; set; }
        [DisplayName("Nome do Veterinário:")]
        public string nm_vet { get; set; }
        [DisplayName("Telefone do Veterinário:")]
        public string tel_vet { get; set; }
    }
}