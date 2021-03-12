﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppBancoDominio
{
    public class BanhoTosa
    {
        [DisplayName("Código do Animal:")]
        public int cd_Animal { get; set; }
        [DisplayName("Raça do Animal:")]
        public string raca_animal { get; set; }
        [DisplayName("Nome do Cliente:")]
        public string nm_cli { get; set; }
        [DisplayName("CPF do Cliente:")]
        [StringLength(14, MinimumLength = 14, ErrorMessage = "Esse campo deve ter 11 caracteres!")]
        public string cpf_cli { get; set; }
        [DisplayName("Endereço do Cliente:")]
        public string end_cli { get; set; }
        [DisplayName("Telefone do Cliente:")]
        public string tel_cli { get; set; }
        [DisplayName("Funcionário:")]
        public string nm_func { get; set; }
        [DisplayName("Data:")]
        [Required(ErrorMessage = "Digite a data em que o serviço será realizado!")]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:dd/MM/yyyy}")]
        public DateTime dt_serv { get; set; }
        [DisplayName("Hora:")]
        [Required(ErrorMessage = "Digite a hora em que o serviço será realizado!")]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:HH:mm}")]
        public DateTime hr_serv { get; set; }
        [DisplayName("Forma de pagamento:")]
        public string forma_paga { get; set; }
    }
}
