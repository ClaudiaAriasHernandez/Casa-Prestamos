import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';
import { ClientePage } from '../page/cliente/cliente.po';

describe('workspace-project Cliente', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let cliente: ClientePage;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        cliente = new ClientePage();
    });

    it('Deberia crear cliente', () => {
        const NOMBRE = 'Alex Bustamante';
        const NUMERO_DOCUMENTO = '1023328473';
        const DIRECCION = 'Calle 56 A 35';
        const TELEFONO = '5982256';

        page.navigateTo();
        navBar.clickBotonClientes();
        cliente.clickBotonCrearClientes();
        cliente.ingresarNombre(NOMBRE);
        cliente.ingresaTipoDocumento();
        cliente.ingresaNumerodocumento(NUMERO_DOCUMENTO);
        cliente.ingresaDireccion(DIRECCION);
        cliente.ingresaTelefono(TELEFONO);
        cliente.btnGuardarCliente();
        // Adicionamos las validaciones despues de la creación
        // expect(<>).toEqual(<>);




    });

    it('Deberia listar clientes', () => {
        page.navigateTo();
        navBar.clickBotonClientes();
        cliente.clickBotonListarClientes();


    });
});
