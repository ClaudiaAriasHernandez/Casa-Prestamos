import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';
import { PrestamoPage } from '../page/prestamo/prestamo.po';

describe('workspace-project Prestamo', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let prestamo: PrestamoPage;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        prestamo = new PrestamoPage();
    });

    it('Deberia crear prestamo', () => {
        const NUMERO_DOCUMENTO = '1023328473';
        const VALOR = '1000000';
        const MENSAJE = 'Se creo el prestamo al cliente de forma exitosa';

        page.navigateTo();
        navBar.clickBotonPrestamos();
        prestamo.clickBotonCrearPrestamo();
        prestamo.ingresaNumerodocumento(NUMERO_DOCUMENTO);
        prestamo.ingresaTipoDocumento();
        prestamo.btnBuscarCliente();
        prestamo.ingresaValor(VALOR);

        expect(  prestamo.btnGuardarPrestamo()).toContain(MENSAJE);

      });

    it('Deberia listar Prestamos', () => {
        page.navigateTo();
        navBar.clickBotonPrestamos();
        prestamo.clickBotonListarPrestamos();


    });
});
