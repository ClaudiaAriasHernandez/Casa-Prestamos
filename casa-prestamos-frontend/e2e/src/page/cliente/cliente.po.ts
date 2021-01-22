import { by, element } from 'protractor';

export class ClientePage {
    private linkCrearCliente = element(by.id('linkCrearCliente'));
    private crearCliente = element(by.id('crearCliente'));
    private linkListarClientes = element(by.id('linkListarClientes'));
    private inputNombreCliente = element(by.id('nombre'));
    private inputTipoDocumentoCliente = element(by.id('tipoDocumento'));
    private inputNumeroDocumentoCliente = element(by.id('numeroDocumento'));
    private inputDireccionCliente = element(by.id('direccion'));
    private inputTelefonoCliente = element(by.id('telefono'));


    async clickBotonCrearClientes() {
        await this.linkCrearCliente.click();
    }

    async clickBotonListarClientes() {
        await this.linkListarClientes.click();
    }

    async btnGuardarCliente() {
        await this.crearCliente.click();
        this.getMensaje();
    }

    async ingresarNombre(nombre) {
        await this.inputNombreCliente.sendKeys(nombre);
    }

    async ingresaTipoDocumento() {

      this.inputTipoDocumentoCliente.click().then(() => {

        this.inputTipoDocumentoCliente.element(by.id('idTipoDocumento'))
          .getAttribute('aria-owns').then((optionIdsString: string) => {
            const optionIds = optionIdsString.split(' ');

            for (const optionId of optionIds) {
              const option = element(by.id(optionId));
              option.getText().then((text) => {
                if (text === 'CC') {
                  option.click();
                }
              });
            }
          });
      });

    }

    async ingresaNumerodocumento(numeroDocumento) {
      await this.inputNumeroDocumentoCliente.sendKeys(numeroDocumento);
    }

    async ingresaDireccion(direccion) {
      await this.inputDireccionCliente.sendKeys(direccion);
    }

    async ingresaTelefono(telefono) {
      await this.inputTelefonoCliente.sendKeys(telefono);
    }

    getMensaje() {
      const MENSAJE = 'Se creo el cliente de forma exitosa';

      expect(element(by.tagName('simple-snack-bar')).getText() as Promise<string>).toContain(MENSAJE);

     }
}
