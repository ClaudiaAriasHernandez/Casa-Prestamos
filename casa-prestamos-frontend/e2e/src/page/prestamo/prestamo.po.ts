import { by, element } from 'protractor';

export class PrestamoPage {
    private linkCrearPrestamo = element(by.id('linkCrearPrestamo'));
    private crearPrestamo = element(by.id('crearPrestamo'));
    private linkListarPrestamos = element(by.id('linkListarPrestamos'));
    private buscarCliente = element(by.id('buscarCliente'));
    private inputTipoDocumentoCliente = element(by.id('tipoDocumento'));
    private inputNumeroDocumentoCliente = element(by.id('numeroDocumento'));
    private inputValorPrestamo = element(by.id('valor'));


    async clickBotonCrearPrestamo() {
        await this.linkCrearPrestamo.click();
    }

    async clickBotonListarPrestamos() {
        await this.linkListarPrestamos.click();
    }

    async btnGuardarPrestamo() {
        await this.crearPrestamo.click();
        this.getMensaje();
    }

    async btnBuscarCliente() {
      await this.buscarCliente.click();
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

    async ingresaValor(valor) {
      await this.inputValorPrestamo.sendKeys(valor);
    }

     getMensaje() {
      const MENSAJE = 'Se creo el prestamo al cliente de forma exitosa';

      expect(element(by.tagName('simple-snack-bar')).getText() as Promise<string>).toContain(MENSAJE);

    }
}
