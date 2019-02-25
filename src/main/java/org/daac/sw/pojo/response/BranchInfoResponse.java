package org.daac.sw.pojo.response;

import lombok.Data;
import org.daac.sw.pojo.Version;

import java.util.List;

/**
 * Created by  HASEE on 2019-01-25
 */
@Data
public class BranchInfoResponse  extends AbaseResponse{
    private List<Version> version;
}
