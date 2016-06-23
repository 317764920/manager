/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

/**
 * 
  * @ClassName(类名)      : AliasIQ
  * @Description(描述)    : 别名iq
  * @author(作者)         ：liuchunxu
  * @date (开发日期)      ：2015年6月17日 上午9:44:09
  *
 */
public class AliasIQ extends IQ {

    private String alias;
    
    private String username;

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("Alias").append(" xmlns=\"").append(
                "androidpn:iq:alias").append("\">");
        if (alias != null) {
            buf.append("<alias>").append(alias).append("</alias>");
        }
        if (username != null) {
            buf.append("<username>").append(username).append("</username>");
        }
        buf.append("</").append("Alias").append("> ");
        return buf.toString();
    }

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
