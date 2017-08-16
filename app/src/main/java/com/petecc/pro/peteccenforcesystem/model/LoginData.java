package com.petecc.pro.peteccenforcesystem.model;

/**
 * Created by daiyf on 2017/4/19.
 * 登录返回的数据中的data
 */

public class LoginData {
        /**
         * encryptinfo : y7SbRgN6gxMUGahrKWPIMhpPF++nH1bvufy8LFpy
         * sessionid : a199f5e6862b514882c8c6a84b1e4ef3
         * userinfo : {"authority":"","avatar":"","createtime":"2017-04-25 11:27:07","id":"1008336","name":"","status":"1","updatetime":"2017-04-25 11:27:07","usertype":"0"}
         */
        private String encryptinfo;
        private String sessionid;
        private UserinfoBean userinfo;

        public String getEncryptinfo() {
            return encryptinfo;
        }

        public void setEncryptinfo(String encryptinfo) {
            this.encryptinfo = encryptinfo;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * authority :
             * avatar :
             * createtime : 2017-04-25 11:27:07
             * id : 1008336
             * name :
             * status : 1
             * updatetime : 2017-04-25 11:27:07
             * usertype : 0
             */

            private String authority;
            private String avatar;
            private String createtime;
            private String id;
            private String name;
            private String status;
            private String updatetime;
            private String usertype;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }
        }
}
