# PsiArbFinal
Triangular arbitrage scanner for digital currency market.

Triangular arbitrage is a sequence of transactions buying and selling
currencies in an attempt to gain a profit between the mispricing of
three separate exchange rates. For example, It should follow that: 

            EURUSD * USDJPY != EURJPY 
           
Then there would be an arbitrage profit opportunity available. This
application gathered bid/ask prices from Investing.com to perform
the needed caluclations across various digital assets in the cryptocurrency
market.

In practice, triangular arbitrage is unprofitable in the foreign currency market
due to the market's efficiency in pricing. However since there are many different
cryptocurrency exchanges, this given market tends to be less efficient and therefor
offers many profitable arbitrage opportunities.

In order to successfully trade
triangular arbitrage in the cryptocurrency market, it is ideal to automate
order execution due to time constraints of arbitrage spreads staying open.
The average triangular arbitrage spread is 1% wide, with as large as 2%.


