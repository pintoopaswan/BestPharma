import { Component } from '@angular/core';

@Component({
 selector: 'pie-chart',
 templateUrl: '../templates/pie-chart.html'
})
export class PieChartComponent {
 // Pie
 public pieChartLabels:string[] = ['Transfer In', 'Transfer Out', 'Transfer Completed'];
 public pieChartData:number[] = [300, 500, 100];
 public pieChartType:string = 'pie';

 // events
 public chartClicked(e:any):void {
   console.log(e);
 }

 public chartHovered(e:any):void {
   console.log(e);
 }
}