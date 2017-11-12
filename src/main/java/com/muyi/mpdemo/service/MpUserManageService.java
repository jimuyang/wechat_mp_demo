package com.muyi.mpdemo.service;

import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

import java.util.List;

public interface MpUserManageService {

    boolean createTag(String tag);

    List<WxUserTag> getCreatedTagList();

    boolean updateTag(long tagID,String s);

    boolean deleteTag(long tagID);

    WxTagListUser getTagUserList(long tagID, String nextOpenID);

    boolean addUserListToTag(long tagID,List<String> openIDList);

    boolean removeUserListToTag(long tagID,List<String> openIDList);

    List<Long> getTagListByUser(String openID);

    WxMpUser getUserInfo(String openID);

    List<WxMpUser> getUserInfoList(List<String> openIDList);

    List<WxMpUser> getUserInfoList(WxMpUserQuery query);



}
