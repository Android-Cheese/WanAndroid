package com.nyw.wanandroid.module.knowledge.mvp;

import com.bakerj.base.BasePresenter;
import com.bakerj.base.BaseView;
import com.nyw.domain.domain.bean.response.home.KnowledgeArtBean;
import com.nyw.domain.domain.bean.response.home.KnowledgeNavBean;

import java.util.List;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public interface knowledgeNaviContract {
    interface View extends BaseView {
        void NaviBeanGet(List<KnowledgeNavBean> data);
    }

    abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void getNaviBean();
    }
}