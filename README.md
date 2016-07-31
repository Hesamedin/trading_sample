[![Build Status](https://www.bitrise.io/app/4886abd509866ee0.svg?token=8P5LaRGmYu-Mn60dy7QGlw&branch=master)](https://www.bitrise.io/app/4886abd509866ee0)

## Build Info:
Version_Code:1

Version_Name:1.0
                      
                      _.--.
                  _.-'_:-'||
              _.-'_.-::::'||
         _.-:'_.-::::::'  ||
       .'`-.-:::::::'     ||
      /.'`;|:::::::'      ||_
     ||   ||::::::'     _.;._'-._
     ||   ||:::::'  _.-!oo @.!-._'-.
     \'.  ||:::::.-!()oo @!()@.-'_.|
      '.'-;|:.-'.&$@.& ()$%-'o.'\U||
        `>'-.!@%()@'@_%-'_.-o _.|'||
         ||-._'-.@.-'_.-' _.-o  |'||
         ||=[ '-._.-\U/.-'    o |'||
         || '-.]=|| |'|      o  |'||
         ||      || |'|        _| ';
         ||      || |'|    _.-'_.-'
         |'-._   || |'|_.-'_.-'
         '-._'--.|| |' `_.-'
              '-.||_/.-'

## MobiCoin Exchange Challenge

MobiCoin is the future of currency, but more importantly, people really want to
speculate on the value of their MobiCoins in Canadian Dollars.

Rumor has it, MobiCoin is going to the moon!

To capitalize on this frenzy, you are tasked with **creating an electronic
exchange where people can post buy or sell orders**.


### How Exchanges Match Orders

A short overview about how exchanges match buy and sell orders.

An exchange maintains two sorted lists of orders:

1. A **buy list** ordered highest-to-lowest price. It contains orders from
people who are willing to buy at a given price and quantity.

2. A **sell list** ordered lowest-to-highest price. It contains orders from
people who are willing to sell at a given price and quantity.

### Example

    BUY
    ====

    100 MOC at $10.00   CAD/MOC
     50 MOC at $ 9.50   CAD/MOC
     20 MOC at $ 8.99   CAD/MOC

    SELL
    ====

     10 MOC at $11.00   CAD/MOC
    500 MOC at $12.50   CAD/MOC
    200 MOC at $13.49   CAD/MOC

In the above exchange, there is nobody willing to buy at the lowest selling
price ($11.00), nor is there anyone willing to sell at highest buying
price ($10.00), thus no transaction can take place.

If a new order comes in at say, BUY 20 MOC at $11.50 CAD, then there is a buyer
willing to pay what some seller is selling for ($11.00 CAD/MOC). Thus, a
transaction happens.

So, we match this buy order up with the first order in the sell list. We can
transact 10 MOC at the price $11.00 per MOC.

However, there is still a buy order for 10 MOC unfilled at the price
of $11.50, because we cannot fill it at the next highest SELL price ($12.50).
Therefore, the remaining BUY order goes at the top of the BUY list.

The exchange now looks like this:

    BUY
    ====

     10 MOC at $11.50   CAD/MOC
    100 MOC at $10.00   CAD/MOC
     50 MOC at $ 9.50   CAD/MOC
     20 MOC at $ 8.99   CAD/MOC

    SELL
    ====

    500 MOC at $12.50   CAD/MOC
    200 MOC at $13.49   CAD/MOC

If an order comes in that satisfies no current SELL or BUY price, it is
placed in the SELL or BUY lists at the appropriate point so that the lists
remain sorted.

When a transaction is compeleted, we store the result in a log so we can
reconcile it later with people's account balances.


### Suggested Order to Approach

We're providing you with a stub implementation in `Exchange.java`.

There are a few functions to start you off. Each of these take the following
arguments:

- origin: the user that placed the order.
- price: the price per mobicoin.
- quantity: the number of mobicoins to transact.

Please implement the necessary functionality in the `Exchange` stub class.
Stub tests can be found in `ExchangeTest.java`.

Feel free to extend or change the `Exchange` class methods or modify the test
suite. Use whatever libraries you need to make it work.

Good luck!

### What we'd like to see in your solution:

- At least some of the functionality implemented and working
- Tests for that functionality
- Be prepared to explain your choices and compromises
- We'd rather see something that works than something that's perfect
- We'll ask you to provide us with a copy of your code at the end
- If you would like any clarification, please ask
