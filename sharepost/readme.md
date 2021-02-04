# Share & Repost

In order to boost user engagement, to allow users to share / repost a post is an organic next step when
users find a post is worthy of being noticed, either to appeal for different voices or to express the concur to the notion.


### 1. Sharing Setting
You can decide where a post from each origin (my timeline / private community / public community) can be shared to. 

### Enum
#### EkoPostSharingTarget
`OriginFeed, MyFeed, PublicCommunity, PrivateCommunity, External`

Use enums to enable the target(s) that a post can be shared to.


#### EkoPostSharingSetting

#### My Feed
Use `settings.myFeedPostSharingTarget`, to define sharing destinations for posts that were created on my feed.
Note: EkoPostSharingTarget.Origin will be ignored for this settings

#### Private Community
Use `settings.privateCommunityPostSharingTarget`, to define sharing destinations for posts that were created on private community

#### Public Community
Use `settings.publicCommunityPostSharingTarget`, to define sharing destinations for posts that were created on public community


#### Other User feed
Use `settings.userFeedPostSharingTarget`, to define sharing destinations for posts that were created on other users' feed


##### Example:
```Kotlin
   // Define that posts created ony my feed can be shared within my feed and to external destinations only.
   val settings = EkoPostSharingSettings()
   settings.myFeedPostSharingTarget = listOf(EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.External)
   EkoUIKitClient.feedUISettings.postSharingSettings = settings
```


Note: If you won't calling setting. The UIKit will use default value as follows:

```Kotlin
var privateCommunityPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed)
var publicCommunityPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
var myFeedPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
var userFeedPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
```

### 2. Share Out
You can implement Share out feature by adding EkoPostSharingTarget.External to the corresponding EkoPostSharingSetting that you'd like to enable "More option" menu. With `IPostShareClickListener` interface you can implement `shareToExternal(context: Context, post: EkoPost)` function to handle on "More options" menu clicked event.

If you want to apply the implementation to `Global Level` setting:

```Kotlin
// In your Application class

EkoUIKitClient.feedUISettings.postShareClickListener = object : IPostShareClickListener {
   override fun shareToExternal(context: Context, post: EkoPost) {
       //You can generate deeplink from postId and launch share intent
   }
}
```

If you want to apply the implementation to `Component Level` setting, you have to provide the implementation at `.postShareClickListener(this)` function via the component Builder.

Components that take IPostShareClickListener:
   1. `EkoMyFeedFragment`
   2. `EkoCommunityFeedFragment` 
   3. `EkoGlobalFeedFragment` 
   4. `EkoPostDetailFragment` 
   5. `EkoUserFeedFragment` 


##### Example:
```Kotlin
class YourFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = EkoCommunityFeedFragment.Builder()
            .community(:community)
            .postShareClickListener(object : IPostShareClickListener {
                override fun shareToExternal(context: Context, post: EkoPost) {
                  //You can generate deeplink from postId and launch share intent
               }
            })
            .build(activity as AppCompatActivity)
    }
    
}
```

### 3. Share In       
You can parse postId from your deeplink, and use it to navigate to Post detail page.

##### Example:

```Kotlin
   val fragment = EkoPostDetailFragment.Builder()
            .postId(:postId)
            .build(activity)
```
            


