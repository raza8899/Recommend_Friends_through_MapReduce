# Recommend_Friends_through_MapReduce
Its a Map Reduce Program which tells you about People you may know on the basis of mutual friends,so it basically recommends you the friends that you may know on a social networking platform.
->Input File Format
Input File conatins Input in the form of adjaceny list
<SocialNetworkingSiteUser><tabspace><Friends separated by comma>
 e.g   0  1,2,3,4,5,6
  wherer 0 is the user and 1,2,3,4,5,6 are his friends 
  
  Problem Statement
  Basically we have to suggest 10 peoples to a user which are still not friends but have maximum mutual friends in common with the User
  
 OutputFormat
 
 User<Tabspace><Recommendation separated with comma>
  0   123,3128,20,45......//here 123 has maximum mutual friends in common with 0
  
  Recommendations must be sorted in descending order of number of mutual friends
  If there are equal mutual friends Recommendations should be sorted on  in numericallly ascending order on the basis of userID
  e.g 12  123,1987 //let's say both have same number of mutual friend in common with 12 then 123 should come first as its numerically less 
  
