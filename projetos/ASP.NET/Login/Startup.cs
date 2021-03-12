using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Login.Startup))]
namespace Login
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
