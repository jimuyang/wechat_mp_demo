package com.muyi.mpdemo.service.impl;

import com.muyi.mpdemo.exception.MpException;
import com.muyi.mpdemo.service.MpUserManageService;
import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("mpUserManageService")
public class MpUserManageServiceImpl implements MpUserManageService {


    @Autowired
    private WxMpService wxMpService;

    //创建一个tag
    public boolean createTag(String tag){
        try {
            WxUserTag wxUserTag = wxMpService.getUserTagService().tagCreate(tag);
            log.info(JsonUtil.toJson(wxUserTag));

        }catch (WxErrorException e ){
            throw new MpException(e);
        }
        return true;
    }

    //已创建的tag列表
    public List<WxUserTag> getCreatedTagList(){
        try{
            List<WxUserTag> list = wxMpService.getUserTagService().tagGet();
            log.info(JsonUtil.toJson(list));
            return list;
        }catch (WxErrorException e){
            throw new MpException(e);
        }

    }
    //更新tag
    public boolean updateTag(long tagID,String s){
        try{
            return wxMpService.getUserTagService().tagUpdate(tagID,s);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //删除tag
    public boolean deleteTag(long tagID){
        try{
            return wxMpService.getUserTagService().tagDelete(tagID);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //获取标签下粉丝列表
    public WxTagListUser getTagUserList(long tagID,String nextOpenID){
        try{
            WxTagListUser tagListUser = wxMpService.getUserTagService().tagListUser(tagID,nextOpenID);
            log.info("【count】:{}\n【next_openID】:{}\n【openID list】:{}",
                    tagListUser.getCount(),
                    tagListUser.getNextOpenid(),
                    tagListUser.getData().getOpenidList());
            return tagListUser;
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //批量为用户打上标签
    public boolean addUserListToTag(long tagID,List<String> openIDList){
        try{
            return wxMpService.getUserTagService().batchTagging
                    (tagID,openIDList.stream().toArray(String[]::new));
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //批量为用户取消标签
    public boolean removeUserListToTag(long tagID,List<String> openIDList){
        try {
            return wxMpService.getUserTagService().batchUntagging(
                    tagID,openIDList.stream().toArray(String[]::new));
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //获取用户身上的标签列表
    public List<Long> getTagListByUser(String openID){
        try {
            return wxMpService.getUserTagService().userTagList(openID);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //获取关注用户列表
    public WxMpUserList getSubscribeUserList(String nextOpenID){
        try {
            return wxMpService.getUserService().userList(nextOpenID);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }


    //获取用户基本信息
    public WxMpUser getUserInfo(String openID){
        try {
            return wxMpService.getUserService().userInfo(openID);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //批量获取用户基本信息
    public List<WxMpUser> getUserInfoList(List<String> openIDList){
        try {
            return wxMpService.getUserService().userInfoList(openIDList);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }

    //批量获取用户基本信息
    public List<WxMpUser> getUserInfoList(WxMpUserQuery query){
        try {
            return wxMpService.getUserService().userInfoList(query);
        }catch (WxErrorException e){
            throw new MpException(e);
        }
    }










}
