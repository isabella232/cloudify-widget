/*
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package beans.config;

import beans.GsMailer;
import utils.Utils;

import java.io.File;

/**
 * User: guym
 * Date: 12/13/12
 * Time: 1:32 PM
 */
public class Conf {

    public ApplicationConfiguration application = new ApplicationConfiguration();

    public SmtpConf smtp = new SmtpConf();

    // who is sending the mail?
    public GsMailer.Mailer mailer = new GsMailer.Mailer();

    @Config( ignoreNullValues =  true )
    public String demoUserEmailSuffix = "_demo@gigaspaces.com";

    public ServerConfig server = new ServerConfig();

    public FeaturesConfig features = new FeaturesConfig();

    public SettingsConfig settings = new SettingsConfig();

    @Config(ignoreNullValues = true)
    public boolean sendErrorEmails = false;

    @Config( playKey = "spring.context")
    public String springContext = null;

    public CloudifyConfiguration cloudify = new CloudifyConfiguration();

    public String mixpanelApiKey = null;

    @Config(playKey = "application.secret")
    public String applicationSecret;


    public static class SettingsConfig{
        @Config( ignoreNullValues = true )
        public boolean expireSession = false; // do not use the session expired mechanism.

        public InitialData initialData = new InitialData();

        @Config(ignoreNullValues = true)
        public long stopTimeout = Utils.parseTimeToMillis( "30s" );

        public Boolean globalSecurityCheck = false; // todo : switch this on once feature is completed.


    }

    public static class InitialData{
        @Config( ignoreNullValues = true )
        public boolean load = true ;

        public String defaultPassword = "Initial4657"; // if we need to create users, we will use this password by default.

        @Config( ignoreNullValues = true )
        public boolean override = false; // true iff we want to write over existing objects
    }

    public static class FeaturesConfigItem {
        public String users = ".*"; // all users by default
        @Config( ignoreNullValues = true )
        public boolean on = false ;

        public FeaturesConfigItem setUsers(String users) {
            this.users = users;
            return this;
        }

        public FeaturesConfigItem setOn(boolean on) {
            this.on = on;
            return this;
        }
    }

    public static class FeaturesConfig {
        public FeaturesConfigItem socialLogin = new FeaturesConfigItem();

        public FeaturesConfigItem autoGeneratedRecipeName = new FeaturesConfigItem();
    }

    public static class CloudifyConfiguration{

        public String version = "2.7.0";

        public long deployWatchDogProcessTimeoutMillis = Utils.parseTimeToMillis( "2mn" );
        
        public long bootstrapCloudWatchDogProcessTimeoutMillis = Utils.parseTimeToMillis( "2mn" );

        public File deployScript=Utils.getFileByRelativePath( "/bin/deployer.sh" );

        public File uninstallServiceScript = Utils.getFileByRelativePath( "/bin/uninstall_service.sh" );

        public File uninstallApplicationScript = Utils.getFileByRelativePath( "/bin/uninstall_application.sh" );

        public String removeOutputLines = "";

        public String removeOutputString = "";

        @Config(ignoreNullValues = true)
        public long verifyLoginUserIdTimeout = Utils.parseTimeToMillis("20s");
    }


}
