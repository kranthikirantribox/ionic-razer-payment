import { Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { FormsModule } from '@angular/forms';
import { RazerPayment } from 'razerpaymentplugin';

import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent, FormsModule]
})
export class HomePage {
  merchanthost: string = '';
  opType: string = '';
  currency: string = '';
  amount: number = 0;
  orderId: string = '';
  channel: string = '';
  tid: string = '';
  results_data: string[] = [];
  constructor(private router: Router) {}

  ngOnInit() {
    this.randomnumber();
    this.results_data = ["Status Null"]
    setInterval(() => {
      this.background();
      this.resultnewpage();
    }, 1000); // Call the background function every 5 seconds
  }

 async onButtonClick() {
    let updateCredential_value = [{
    "MerchantHost":this.merchanthost,
    "OpType":this.opType,
    "Currency":this.currency, 
    "Amount":this.amount,
    "OrderId":this.orderId,
    "Channel":this.channel,
    "Tid":this.tid}];
        try {
          const response = await RazerPayment.start({ value: JSON.stringify(updateCredential_value)});
          console.log('Returning', JSON.stringify(response));
        } catch (error) {
          console.error('Error:', error);
        }
  }

  async background(){
    try {
      const response = await RazerPayment.status({ value: "NA"});
      console.log('Returning', JSON.stringify(response));
      this.results_data = [JSON.stringify(response)];
        // this.router.navigate(['/result'])
    
    } catch (error) {
      console.error('Error:', error);
    }
  }

  async resultnewpage(){
    try {
       const responce = this.results_data
       console.log("33333333333333333",responce);
      
    
    } catch (error) {
      console.error('Error:', error);
    }
  }

  async randomnumber(){
    let randomNumber = '';
    for (let i = 0; i < 16; i++) {
      // Generate a random digit (0-9) and append it to the string
      randomNumber += Math.floor(Math.random() * 10);
    }
    // console.log(randomNumber);
    this.tid = randomNumber;
  }
  


  
  
}
