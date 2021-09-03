class BaseScmAdapter {

    BaseScmAdapter() {

    }

    Config createNewConfig() {
        return Config()
    }

    class Config {
        String requireBranch = 'master'
        def pushToRemote = 'origin' // needs to be def as can be boolean or string
        def pushOptions = []
        boolean signTag = false

        /** @deprecated Remove in version 3.0 */
        @Deprecated
        boolean pushToCurrentBranch = false
        String pushToBranchPrefix
        boolean commitVersionFileOnly = false

        void setProperty(String name, Object value) {
            if (name == 'pushToCurrentBranch') {
                project.logger?.warn("You are setting the deprecated and unused option '${name}'. You can safely remove it. The deprecated option will be removed in 3.0")
            }

            metaClass.setProperty(this, name, value)
        }
    }
}
