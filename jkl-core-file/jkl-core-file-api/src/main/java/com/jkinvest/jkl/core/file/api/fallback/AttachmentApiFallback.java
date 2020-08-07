package com.jkinvest.jkl.core.file.api.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.core.file.api.AttachmentApi;
import com.jkinvest.jkl.core.file.dto.AttachmentDTO;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class AttachmentApiFallback implements AttachmentApi {
    @Override
    public R<AttachmentDTO> upload(MultipartFile file, Boolean isSingle, Long id, String bizId, String bizType) {
        return R.timeout();
    }
}
