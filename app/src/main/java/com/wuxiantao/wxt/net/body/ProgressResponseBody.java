package com.wuxiantao.wxt.net.body;

import com.wuxiantao.wxt.net.callback.ReqProgressCallBack;
import com.wuxiantao.wxt.utils.FormatUtils;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ProgressResponseBody
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-28 上午10:59
 * Description:${DESCRIPTION} 文件下载 进度
 */
public class ProgressResponseBody extends ResponseBody {

    private ResponseBody mResponseBody;
    private BufferedSource mBufferedSource;
    private ReqProgressCallBack callBack;

    public ProgressResponseBody(ResponseBody body,ReqProgressCallBack callBack){
        this.mResponseBody = body;
        this.callBack = callBack;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null){
            mBufferedSource = Okio.buffer(new ProgressSource(mResponseBody.source()));
        }
        return mBufferedSource;
    }


    private class ProgressSource extends ForwardingSource{

        private long totalSize = 0;

        public ProgressSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long currentSize = super.read(sink,byteCount);
            // read() returns the number of bytes read, or -1 if this source is exhausted.
            totalSize += currentSize != -1 ? currentSize : 0;
            //当前下载的百分比进度
            int progress = (int) (totalSize * 100 / mResponseBody.contentLength());
            if (callBack != null){
                callBack.onProgress(FormatUtils.bytes2kb(mResponseBody.contentLength()),
                        FormatUtils.bytes2kb(totalSize),
                        progress + "%",currentSize == -1);
            }
            return currentSize;
        }
    }


}
