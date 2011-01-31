//
//  Bloks_iphoneAppDelegate.h
//  Bloks-iphone
//
//  Created by antonio Munoz on 15/01/11.
//  Copyright none 2011. All rights reserved.
//

#import <UIKit/UIKit.h>

@class RootViewController;

@interface Bloks_iphoneAppDelegate : NSObject <UIApplicationDelegate> {
	UIWindow			*window;
	RootViewController	*viewController;
}

@property (nonatomic, retain) UIWindow *window;

@end
