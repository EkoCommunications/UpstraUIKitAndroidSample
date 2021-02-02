# Share & Repost

In order to boost user engagement, to allow users to share / repost a post is an organic next step when
users find a post is worthy of being noticed, either to appeal for different voices or to express the concur to the notion.

## Installation

In your app/build.gradle file add a dependency on one of the UIKit libraries.

```groovy
dependencies {
   implementation 'com.github.EkoCommunications.UpstraUIKitAndroid:upstra-uikit:1.8.3-beta06'
}
```

## Usage
### Share Out
   

### Share In       
If you want to be able to navigate to post detail page by using post id as the key

So that you can implement deeplink from your side and jump to a specific page detail page by calling the following this:


```Kotlin
   val intent = Intent(this, EkoPostDetailsActivity::class.java)
   intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, :postId)
   startActivity(intent)
```
            


