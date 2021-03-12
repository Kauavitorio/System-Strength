using AppBancoDLL;
using AppBancoDominio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PalacePetz.Controllers
{
    public class InicioController : Controller
    {
        //GET: Inicio
        
        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Login(Login login)
        {
            if (ModelState.IsValid)
            {
                if(login.user_login == "FuncPP2020" && login.senha_login == "FuncDS2020")
                {
                    var metodoLogin = new LoginDAO();
                    return RedirectToAction("Index");
                }
                else{
                    ModelState.AddModelError("senha_login", "Usuário ou Senha inválidos!");
                }
            }
            return View(login);
        }
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult VerificaGer()
        {
            return View();
        }
        [HttpPost]
        public ActionResult VerificaGer(Gerente gerente)
        {
            if (ModelState.IsValid)
            {
                if(gerente.senha_ger == "GerFunc2020")
                {
                    var metodoGer = new GerenteDAO();
                    return RedirectToAction("CadastrarFunc");
                }
                else
                {
                    ModelState.AddModelError("senha_ger", "Senha de Gerente Inválida!");
                }
            }
            return View(gerente);
        }

        public ActionResult CadastrarFunc()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CadastrarFunc(Funcionario funcionario)
        {
            if (ModelState.IsValid)
            {
                var metodoFuncionario = new FuncionarioDAO();
                metodoFuncionario.Insert(funcionario);
                return RedirectToAction("FuncCadastrados");
            }
            return View(funcionario);
        }
        public ActionResult FuncCadastrados()
        {
            var metodoFuncionario = new FuncionarioDAO();
            var todosFuncionarios = metodoFuncionario.Listar();
            return View(todosFuncionarios);
        }
        public ActionResult EditarFunc(int id)
        {
            var metodoFuncionario = new FuncionarioDAO();
            var funcionarios = metodoFuncionario.ListarId(id);
            if(funcionarios == null)
            {
                return HttpNotFound();
            }
            return View(funcionarios);
        }
        [HttpPost]
        public ActionResult EditarFunc(Funcionario funcionario)
        {
            if (ModelState.IsValid)
            {
                var metodoFuncionario = new FuncionarioDAO();
                metodoFuncionario.Atualizar(funcionario);
                return RedirectToAction("FuncCadastrados");
            }
            return View(funcionario);
        }
        public ActionResult DetalhesFunc(int id)
        {
            var metodoFuncionario = new FuncionarioDAO();
            var funcionario = metodoFuncionario.ListarId(id);
            if(funcionario == null)
            {
                return HttpNotFound();
            }
            return View(funcionario);
        }
        public ActionResult ExcluirFunc(int id)
        {
            var metodoFuncionario = new FuncionarioDAO();
            var funcionario = metodoFuncionario.ListarId(id);

            if(funcionario == null)
            {
                return HttpNotFound();
            }
            return View(funcionario);
        }
        [HttpPost, ActionName("ExcluirFunc")]
        public ActionResult ExcluirFuncConf(int id)
        {
            var metodoFuncionario = new FuncionarioDAO();
            Funcionario funcionario = new Funcionario();
            funcionario.id_func = id;
            metodoFuncionario.Excluir(funcionario);
            return RedirectToAction("FuncCadastrados");
        }

        //CLIENTE

        public ActionResult CadastrarCli()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CadastrarCli(Cliente cliente)
        {
            if (ModelState.IsValid)
            {
                var metodoCliente = new ClienteDAO();
                metodoCliente.Insert(cliente);
                return RedirectToAction("ClientesCadastrados");
            }
            return View(cliente);
        }
        public ActionResult ClientesCadastrados()
        {
            var metodoCliente = new ClienteDAO();
            var todosClientes = metodoCliente.Listar();
            return View(todosClientes);
        }
        public ActionResult EditarCli(int id)
        {
            var metodoCliente = new ClienteDAO();
            var clientes = metodoCliente.ListarId(id);
            if (clientes == null)
            {
                return HttpNotFound();
            }
            return View(clientes);
        }
        [HttpPost]
        public ActionResult EditarCli(Cliente cliente)
        {
            if (ModelState.IsValid)
            {
                var metodoCliente = new ClienteDAO();
                metodoCliente.Atualizar(cliente);
                return RedirectToAction("ClientesCadastrados");
            }
            return View(cliente);
        }
        public ActionResult DetalhesCli(int id)
        {
            var metodoCliente = new ClienteDAO();
            var cliente = metodoCliente.ListarId(id);
            if (cliente == null)
            {
                return HttpNotFound();
            }
            return View(cliente);
        }
        public ActionResult ExcluirCli(int id)
        {
            var metodoCliente = new ClienteDAO();
            var cliente = metodoCliente.ListarId(id);

            if (cliente == null)
            {
                return HttpNotFound();
            }
            return View(cliente);
        }
        [HttpPost, ActionName("ExcluirCli")]
        public ActionResult ExcluirCliConf(int id)
        {
            var metodoCliente = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.id_cli = id;
            metodoCliente.Excluir(cliente);
            return RedirectToAction("ClientesCadastrados");
        }

        //PRODUTOS

        public ActionResult CadastrarProd()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CadastrarProd(Produto produto)
        {
            if (ModelState.IsValid)
            {
                var metodoProduto = new ProdutoDAO();
                metodoProduto.Insert(produto);
                return RedirectToAction("ProdCadastrados");
            }
            return View(produto);
        }
        public ActionResult ProdCadastrados()
        {
            var metodoProduto = new ProdutoDAO();
            var todosProdutos = metodoProduto.Listar();
            return View(todosProdutos);
        }
        public ActionResult EditarProd(int id)
        {
            var metodoProduto = new ProdutoDAO();
            var produtos = metodoProduto.ListarId(id);
            if (produtos == null)
            {
                return HttpNotFound();
            }
            return View(produtos);
        }
        [HttpPost]
        public ActionResult EditarProd(Produto produto)
        {
            if (ModelState.IsValid)
            {
                var metodoProduto = new ProdutoDAO();
                metodoProduto.Atualizar(produto);
                return RedirectToAction("ProdCadastrados");
            }
            return View(produto);
        }
        public ActionResult DetalhesProd(int id)
        {
            var metodoProduto = new ProdutoDAO();
            var produto = metodoProduto.ListarId(id);
            if (produto == null)
            {
                return HttpNotFound();
            }
            return View(produto);
        }
        public ActionResult ExcluirProd(int id)
        {
            var metodoProduto = new ProdutoDAO();
            var produto = metodoProduto.ListarId(id);

            if (produto == null)
            {
                return HttpNotFound();
            }
            return View(produto);
        }
        [HttpPost, ActionName("ExcluirProd")]
        public ActionResult ExcluirProdConf(int id)
        {
            var metodoProduto = new ProdutoDAO();
            Produto produto = new Produto();
            produto.id_prod = id;
            metodoProduto.Excluir(produto);
            return RedirectToAction("ProdCadastrados");
        }
        public ActionResult VendaProd()
        {
            return View();
        }
        [HttpPost]
        public ActionResult VendaProd(VendaProd vendaprod)
        {
            if (ModelState.IsValid)
            {
                var metodoVendaProd = new VendaProdDAO();
                metodoVendaProd.Insert(vendaprod);
            }
            return View(vendaprod);
        }
        public ActionResult RelatorioVen()
        {
            return View();
        }
        public ActionResult Servicos()
        {
            return View();
        }

        //CONSULTA 

        public ActionResult CadastrarConsul()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CadastrarConsul(Consulta consulta)
        {
            if (ModelState.IsValid)
            {
                var metodoConsulta = new ConsultaDAO();
                metodoConsulta.Insert(consulta);
                return RedirectToAction("ConsulCadastrados");
            }
            return View(consulta);
        }
        public ActionResult ConsulCadastrados()
        {
            var metodoConsulta = new ConsultaDAO();
            var todosConsultas = metodoConsulta.Listar();
            return View(todosConsultas);
        }
        public ActionResult EditarConsul(int id)
        {
            var metodoConsulta = new ConsultaDAO();
            var consultas = metodoConsulta.ListarId(id);
            if (consultas == null)
            {
                return HttpNotFound();
            }
            return View(consultas);
        }
        [HttpPost]
        public ActionResult EditarConsul(Consulta consulta)
        {
            if (ModelState.IsValid)
            {
                var metodoConsulta = new ConsultaDAO();
                metodoConsulta.Atualizar(consulta);
                return RedirectToAction("ConsulCadastrados");
            }
            return View(consulta);
        }
        public ActionResult DetalhesConsul(int id)
        {
            var metodoConsul = new ConsultaDAO();
            var consulta = metodoConsul.ListarId(id);
            if (consulta == null)
            {
                return HttpNotFound();
            }
            return View(consulta);
        }
        public ActionResult ExcluirConsul(int id)
        {
            var metodoConsulta = new ConsultaDAO();
            var consulta = metodoConsulta.ListarId(id);

            if (consulta == null)
            {
                return HttpNotFound();
            }
            return View(consulta);
        }
        [HttpPost, ActionName("ExcluirConsul")]
        public ActionResult ExcluirConsulConf(int id)
        {
            var metodoConsulta = new ConsultaDAO();
            Consulta consulta = new Consulta();
            consulta.cd_animal = id;
            metodoConsulta.Excluir(consulta);
            return RedirectToAction("ConsulCadastrados");
        }

        //BANHO E TOSA

        public ActionResult CadastrarBanhoTosa()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CadastrarBanhoTosa(BanhoTosa banhotosa)
        {
            if (ModelState.IsValid)
            {
                var metodoBanhoTosa = new BanhoTosaDAO();
                metodoBanhoTosa.Insert(banhotosa);
                return RedirectToAction("BanhoTosaCadastrados");
            }
            return View(banhotosa);
        }
        public ActionResult BanhoTosaCadastrados()
        {
            var metodoBanhoTosa = new BanhoTosaDAO();
            var todosBanhoTosas = metodoBanhoTosa.Listar();
            return View(todosBanhoTosas);
        }
        public ActionResult EditarBanhoTosa(int id)
        {
            var metodoBanhoTosa = new BanhoTosaDAO();
            var banhotosas = metodoBanhoTosa.ListarId(id);
            if (banhotosas == null)
            {
                return HttpNotFound();
            }
            return View(banhotosas);
        }
        [HttpPost]
        public ActionResult EditarBanhoTosa(BanhoTosa banhotosa)
        {
            if (ModelState.IsValid)
            {
                var metodoBanhoTosa = new BanhoTosaDAO();
                metodoBanhoTosa.Atualizar(banhotosa);
                return RedirectToAction("BanhoTosaCadastrados");
            }
            return View(banhotosa);
        }
        public ActionResult DetalhesBanhoTosa(int id)
        {
            var metodoBanhoTosa = new BanhoTosaDAO();
            var banhotosa = metodoBanhoTosa.ListarId(id);
            if (banhotosa == null)
            {
                return HttpNotFound();
            }
            return View(banhotosa);
        }
        public ActionResult ExcluirBanhoTosa(int id)
        {
            var metodoBanhoTosa = new BanhoTosaDAO();
            var banhotosa = metodoBanhoTosa.ListarId(id);

            if (banhotosa == null)
            {
                return HttpNotFound();
            }
            return View(banhotosa);
        }
        [HttpPost, ActionName("ExcluirBanhoTosa")]
        public ActionResult ExcluirBanhoTosaConf(int id)
        {
            var metodoBanhoTosa = new BanhoTosaDAO();
            BanhoTosa banhotosa = new BanhoTosa();
            banhotosa.cd_Animal = id;
            metodoBanhoTosa.Excluir(banhotosa);
            return RedirectToAction("BanhoTosaCadastrados");
        }

        //INFORMAÇÕES DA LOJA

        public ActionResult Info()
        {
            return View();
        }

        //AJUDA

        public ActionResult Ajuda()
        {
            return View();
        }
    }
}