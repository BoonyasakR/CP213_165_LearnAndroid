import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';

/// Provides Firebase configuration for each supported platform.
class DefaultFirebaseOptions {
  const DefaultFirebaseOptions._();

  /// Returns the [FirebaseOptions] for the current platform if available.
  static FirebaseOptions? get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    return null;
  }

  /// Firebase configuration used by the Flutter web build.
  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyC4S2JpqexG9vYwYVn0camTBcLfQGElLyw',
    appId: '1:22043008629:web:6dd2e5ce8ad67f0ced623c',
    messagingSenderId: '22043008629',
    projectId: 'project-mobile-5a6f5',
    authDomain: 'project-mobile-5a6f5.firebaseapp.com',
    storageBucket: 'project-mobile-5a6f5.firebasestorage.app',
  );
}
