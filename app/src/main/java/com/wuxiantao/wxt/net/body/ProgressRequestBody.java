package com.wuxiantao.wxt.net.body;

import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.utils.FormatUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProgressRequestBody
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午11:08
 * Description:${DESCRIPTION} 文件上传 进度
 */
public class ProgressRequestBody extends RequestBody {

    //实际的待包装请求体
    private  RequestBody mRequestBody;
    //进度回调接口
    private ReqProgressCallBack mCallBack;
    //包装完成的BufferedSink
    private BufferedSink mSink;

    public ProgressRequestBody(RequestBody body, ReqProgressCallBack callBack){
        this.mRequestBody = body;
        this.mCallBack = callBack;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink s) throws IOException {
        if (mSink == null){
            mSink = Okio.buffer(new CountingSink(s));
        }
        //写入
        mRequestBody.writeTo(mSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        mSink.flush();
    }


    private  class CountingSink extends ForwardingSink{
        //当前写入字节数
        long currentSize = 0L;
        //总字节长度，避免多次调用contentLength()方法
        long totalSize = 0L;

        private CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            if (totalSize == 0) {
                //获得contentLength的值，后续不再调用
                totalSize = contentLength();
            }
            //增加当前写入的字节数
            currentSize += byteCount;
            //当前上传的百分比进度
            int progress = (int) (currentSize * 100 / totalSize);
//                int progress = (int) (((float) currentSize) / ((float) totalSize) * 100);
            boolean done = totalSize == currentSize;
            //回调
            if(mCallBack != null){
                mCallBack.onProgress(FormatUtils.bytes2kb(totalSize),
                        FormatUtils.bytes2kb(currentSize), progress + "%",done);
            }
        }
    }

}
