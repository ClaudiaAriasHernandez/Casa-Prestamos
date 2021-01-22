import { by, element } from 'protractor';

export class NavbarPage {
    linkHome = element(by.xpath('/html/body/app-root/app-navbar/mat-toolbar/button[1]'));
    linkCliente = element(by.id('linkCliente'));
    linkPrestamo = element(by.id('linkPrestamo'));

    async clickBotonClientes() {
        await this.linkCliente.click();
    }

    async clickBotonPrestamos() {
      await this.linkPrestamo.click();
  }
}
