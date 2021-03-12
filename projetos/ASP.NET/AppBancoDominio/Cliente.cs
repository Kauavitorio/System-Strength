using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppBancoDominio
{
    public class Cliente
    {
        [DisplayName("Código do Cliente:")]
        public int id_cli { get; set; }
        [DisplayName("CPF do Cliente:")]
        [StringLength(14, MinimumLength = 14, ErrorMessage = "Esse campo deve ter 11 caracteres!")]
        public string cpf_cli { get; set; }
        [DisplayName("Nome do Cliente:")]
        public string nm_cli { get; set; }
        [DisplayName("Endereço do Cliente:")]
        public string end_cli { get; set; }
        [DisplayName("Complemento do Cliente:")]
        public string comple_cli { get; set; }
        [DisplayName("Telefone do Cliente:")]
        public string tel_cli { get; set; }

    }
}
