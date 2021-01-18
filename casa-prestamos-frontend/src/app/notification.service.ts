import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment as ENV} from 'src/environments/environment';

@Injectable()
export class NotificationService {

    private readonly DEFAULT_TIMEOUT = ENV.notification_timeout || 5000;
    private readonly PARAMS: any = {
        duration: this.DEFAULT_TIMEOUT,
        horizontalPosition: 'right',
        verticalPosition: 'bottom',
    };

    constructor(
        private readonly snackBar: MatSnackBar
    ) { }

    private openSnack(message: string, css: string): void {
        this.snackBar.open(message, 'Cerrar', {
            ...this.PARAMS,
            panelClass: css
        });
    }

    success(message: string): void {
        this.openSnack(message, 'green-snack');
    }

    error(message: string): void {
        this.openSnack(message, 'red-snack');
    }

    warning(message: string): void {
        this.openSnack(message, 'yellow-snack');
    }

    info(message: string): void {
        this.openSnack(message, 'info-snack');
    }

}
