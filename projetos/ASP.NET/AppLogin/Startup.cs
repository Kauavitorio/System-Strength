using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(AppLogin.Startup))]
namespace AppLogin
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
