package org.tiramisu.config

object TRemoteConfig: IRemoteConfig by FirebaseRemoteConfig(),
        IKeyValueQuerier by FirebaseKeyValueQuerier()