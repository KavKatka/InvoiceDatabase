export const price = intl.NumberFormat('cs-CZ', {
    style:'currency', 
    currency: 'CZK',
    
});

// Format the price above to USD using the locale, style, and currency.
//let USDollar = new Intl.NumberFormat('en-US', {
 //   style: 'currency',
 //   currency: 'USD',
//});

//console.log(`The formated version of ${price} is ${USDollar.format(price)}`);
// The formated version of 14340 is $14,340.00