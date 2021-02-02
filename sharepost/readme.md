# Share & Repost

In order to boost user engagement, to allow users to share / repost a post is an organic next step when
users find a post is worthy of being noticed, either to appeal for different voices or to express the concur to the notion.

## Installation

In your app/build.gradle file add a dependency on one of the UIKit libraries.

```groovy
dependencies {
   implementation 'com.github.EkoCommunications.UpstraUIKitAndroid:upstra-uikit:1.9.0'
}
```

## Usage

### 1. Sharing Setting
If you want to be able to set a network level setting to enable share settings

So that you can decide whether you want to enable where to share from (timeline / private community / public community) and where to share to (my timeline / my public community / my private community / external)

### Enum
#### EkoPostSharingTarget
`OriginFeed, MyFeed, PublicCommunity, PrivateCommunity, External`

#### 1.1 EkoPostSharingSetting
If you want to be able to set your feed post sharing target by calling the following this:

The settings sharing target expected type `List<EkoPostSharingTarget>`

#### My Feed
`settings.myFeedPostSharingTarget`

##### Example:
```Kotlin
   val settings = EkoPostSharingSettings()
   settings.myFeedPostSharingTarget = listOf(EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.External)
   EkoUIKitClient.feedUISettings.setPostSharingSettings(settings)
```

as well as if you want to setting private community, public community or other user feed by assign value setting following this:

#### Private Community
`settings.privateCommunityPostSharingTarget`

#### Public Community
`settings.publicCommunityPostSharingTarget`

#### Other User feed
`settings.userFeedPostSharingTarget`


Note: If you won't calling setting. The UIKit will use default value as follows:

```Kotlin
var privateCommunityPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed)
var publicCommunityPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
var myFeedPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
var userFeedPostSharingTarget = listOf(EkoPostSharingTarget.OriginFeed, EkoPostSharingTarget.MyFeed, EkoPostSharingTarget.PublicCommunity, EkoPostSharingTarget.PrivateCommunity)
```

### 2. Share Out

From `ISharePostClickListener` class you can override `shareToExternal(context: Context, post: EkoPost)` function for handle share with "More options" menu
by calling the following this:

If you want to set a `Network Level` setting:

##### Example:
```Kotlin
EkoUIKitClient.feedUISettings.feedEventHandler = object : ISharePostClickListener {
   override fun shareToExternal(context: Context, post: EkoPost) {
       //You can implement deeplink and jump to intent share external app from your side
   }
}
```

If you want to set a `Component Level` setting:

In your Activity need to extend `ISharePostClickListener` class and override `shareToExternal(context: Context, post: EkoPost)` function 

##### Example:
```Kotlin
class YourActivity : AppCompatActivity(), ISharePostClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)
    }

    override fun shareToExternal(context: Context, post: EkoPost) {
        super.shareToExternal(context, post)
        //You can implement deeplink and jump to intent share external app from your side
    }
}
```
   

### 3. Share In       
If you want to be able to navigate to post detail page by using post id as the key

So that you can implement deeplink from your side and jump to a specific page detail page by calling the following this:


```Kotlin
   val intent = Intent(this, EkoPostDetailsActivity::class.java)
   intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, :postId)
   startActivity(intent)
```
            


