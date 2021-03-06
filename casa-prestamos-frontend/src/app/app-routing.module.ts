import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SecurityGuard } from '@core/guard/security.guard';
import { HomeComponent } from '@home/home.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [SecurityGuard]  },
  { path: 'producto', loadChildren: () => import('@producto/producto.module').then(mod => mod.ProductoModule) },
  { path: 'cliente', loadChildren: () => import('@cliente/cliente.module').then(mod => mod.ClienteModule) },
  { path: 'tipodocumento', loadChildren: () => import('@tipodocumento/tipodocumento.module').then(mod => mod.TipoDocumentoModule) },
  { path: 'prestamo', loadChildren: () => import('@prestamo/prestamo.module').then(mod => mod.PrestamoModule) },
  { path: '**', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
