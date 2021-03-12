using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace AppBancoDominio
{
    public class VendaProd
    {
        public int cd_venda { get; set; }
        [DisplayName("Nome do Cliente:")]
        [Required(ErrorMessage = "Obrigatório digitar o Nome do Cliente!")]
        public string Nm_cli { get; set; }
        [DisplayName("CPF do Cliente:")]
        [Required(ErrorMessage = "Obrigatório digitar o  CPF do Cliente!")]
        [StringLength(14, MinimumLength = 14, ErrorMessage = "Esse campo deve ter 11 caracteres!")]
        public string Cpf_cli { get; set; }
        [DisplayName("Endereço do Cliente:")]
        [Required(ErrorMessage = "Obrigatório digitar o Endereço do Cliente!")]
        public string End_cli { get; set; }
        [DisplayName("Complemento do Cliente:")]
        public string Comple_cli { get; set; }
        [DisplayName("Telefone do Cliente:")]
        [Required(ErrorMessage = "Obrigatório digitar o Telefone do Cliente!")]
        public string Tel_cli { get; set; }
        [DisplayName("Nome do Produto:")]
        [Required(ErrorMessage = "Obrigatório digitar o nome do Produto!")]
        public string Nm_prod { get; set; }
        [DisplayName("Quantidade do Produto:")]
        [Required(ErrorMessage = "Obrigatório digitar a Quantidade do Produto!")]
        public int Qntd_prod { get; set; }
        [DisplayName("Categoria do Produto:")]
        [Required(ErrorMessage = "Obrigatório digitar a Categoria do Produto!")]
        public string Cat_prod { get; set; }
        [DisplayName("Forma de pagamento:")]
        [Required(ErrorMessage = "Obrigatório digitar a Forma de Pagamento Produto!")]
        public string Forma_paga { get; set; }
    }
}