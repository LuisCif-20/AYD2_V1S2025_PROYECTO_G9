import {Routes} from '@angular/router';
import {ButtonDemo} from './button/buttondemo';
import {ChartDemo} from './chartdemo/chartdemo';
import {FileDemo} from './filedemo/filedemo';
import {FormLayoutDemo} from './formlayoutdemo/formlayoutdemo';
import {InputDemo} from './inputdemo/inputdemo';
import {ListDemo} from './listdemo/listdemo';
import {MediaDemo} from './mediademo/mediademo';
import {MessagesDemo} from './messagedemo/messagesdemo';
import {MiscDemo} from './miscdemo/miscdemo';
import {PanelsDemo} from './panelsdemo/panelsdemo';
import {TimelineDemo} from './timelinedemo/timelinedemo';
import {TableDemo} from './tabledemo/tabledemo';
import {OverlayDemo} from './overlaydemo/overlaydemo';
import {TreeDemo} from './treedemo/treedemo';
import {MenuDemo} from './menudemo/menudemo';

export default [
    {path: 'button', data: {breadcrumb: 'Button'}, component: ButtonDemo},
    {path: 'charts', data: {breadcrumb: 'Charts'}, component: ChartDemo},
    {path: 'file', data: {breadcrumb: 'File'}, component: FileDemo},
    {path: 'formlayout', data: {breadcrumb: 'Form Layout'}, component: FormLayoutDemo},
    {path: 'input', data: {breadcrumb: 'Input'}, component: InputDemo},
    {path: 'list', data: {breadcrumb: 'List'}, component: ListDemo},
    {path: 'media', data: {breadcrumb: 'Media'}, component: MediaDemo},
    {path: 'message', data: {breadcrumb: 'Message'}, component: MessagesDemo},
    {path: 'misc', data: {breadcrumb: 'Misc'}, component: MiscDemo},
    {path: 'panel', data: {breadcrumb: 'Panel'}, component: PanelsDemo},
    {path: 'timeline', data: {breadcrumb: 'Timeline'}, component: TimelineDemo},
    {path: 'table', data: {breadcrumb: 'Table'}, component: TableDemo},
    {path: 'overlay', data: {breadcrumb: 'Overlay'}, component: OverlayDemo},
    {path: 'tree', data: {breadcrumb: 'Tree'}, component: TreeDemo},
    {path: 'menu', data: {breadcrumb: 'Menu'}, component: MenuDemo},
    {path: '**', redirectTo: '/notfound'}
] as Routes;
