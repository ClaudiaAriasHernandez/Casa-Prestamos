import { Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import { MenuItem } from '@core/modelo/menu-item';

@Component({
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {

  @Input()
  items: MenuItem[];
  @Output() openMenu = new EventEmitter<void>();

  constructor() { }

  ngOnInit() {
  }

  openSidenav(): void {
      this.openMenu.emit();
  }

}
