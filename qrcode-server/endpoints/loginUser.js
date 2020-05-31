import schema from "../data/schema";
import crypto from "crypto";
import { cryptConfig } from "../serverconfig";

const loginUser = async (req, res) => {
  try {
    const model = schema["user"];
    const data = req.body;
    const key = decreptVal(data.userKey);
    let payload = await model.findOne({
      where: {
        username: key,
      },
    });
    if (payload !== null) {
      payload = {
        name: payload.name,
        username: payload.username,
      };
      return res.status(200).json({ success: true, data: payload });
    } else {
      return res
        .status(200)
        .json({ success: false, error: "Invalid Credential" });
    }
  } catch (error) {
    return res.status(200).json({ success: false, error: error });
  }
};

const decreptVal = (text) => {
  let iv = new Buffer("0000000000000000");
  var encodeKey = crypto
    .createHash(cryptConfig.algorithm)
    .update(cryptConfig.key, "utf-8")
    .digest();
  var cipher = crypto.createDecipheriv(cryptConfig.password, encodeKey, iv);
  return cipher.update(text, "hex", "utf8") + cipher.final("utf8");
};

export default loginUser;
