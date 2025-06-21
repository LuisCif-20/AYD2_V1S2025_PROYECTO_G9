import {Component} from '@angular/core';
import {NotificationsWidget} from './components/notificationswidget/notificationswidget';
import {StatsWidget} from './components/statswidget/statswidget';
import {RecentSalesWidget} from './components/recentsaleswidget/recentsaleswidget';
import {BestSellingWidget} from './components/bestsellingwidget/bestsellingwidget';
import {RevenueStreamWidget} from './components/revenuestreamwidget/revenuestreamwidget';

@Component({
    selector: 'app-dashboard',
    templateUrl: 'dashboard.html',
    imports: [BestSellingWidget]

})
export class Dashboard {
}
