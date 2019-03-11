####ID: 
      SR-1-FU 
####Priority: 
      B
####Req. ID:
      
####Modul: 
      Search result page
####Description
      Filter by user budget  
   ######Precondition
      Pr1. Go to https://booking.com
      Pr2. Search hotels with valid data
   ######Steps
      Step 1: Select budgets in the filter box
   ######Post
      Ps1. Clear browser cookies
####Expected results
      Ex1. Budgets are selected
      Ex2. There are only hotels with total price greater than nightsCount*minimalBudget  
      Ex3. There are only hotels with total price less than nightsCount*maximalBudget