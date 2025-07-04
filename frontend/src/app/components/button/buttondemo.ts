import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {ButtonModule} from 'primeng/button';
import {ButtonGroupModule} from 'primeng/buttongroup';
import {SplitButtonModule} from 'primeng/splitbutton';

@Component({
    selector: 'app-button-demo',
    standalone: true,
    imports: [ButtonModule, ButtonGroupModule, SplitButtonModule],
    templateUrl: 'buttondemo.html'
})
export class ButtonDemo implements OnInit {
    items: MenuItem[] = [];

    loading = [false, false, false, false];

    ngOnInit() {
        this.items = [{label: 'Update', icon: 'pi pi-refresh'}, {
            label: 'Delete',
            icon: 'pi pi-times'
        }, {label: 'Angular.io', icon: 'pi pi-info', url: 'http://angular.io'}, {separator: true}, {
            label: 'Setup',
            icon: 'pi pi-cog'
        }];
    }

    load(index: number) {
        this.loading[index] = true;
        setTimeout(() => (this.loading[index] = false), 1000);
    }
}
