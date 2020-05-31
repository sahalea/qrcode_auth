import schema from "../data/schema";
import crypto from "crypto";
import { cryptConfig } from "../serverconfig";
const registerUser = async (req, res) => {
  try {
    const model = schema["user"];
    let data = req.body;
    if (await validateEmail(model, data.username)) {
      data.password = setEncrypt(data.password);
      let entity = await model.create(data);
      entity = {
        userKey: setEncrypt(entity.username),
      };
      return res.status(200).json({ success: true, data: entity });
    } else {
      return res
        .status(200)
        .json({ success: false, error: "Username Already Registered" });
    }
  } catch (error) {
    return res.status(200).json({ success: false, error: error });
  }
};

const setEncrypt = (text) => {
  let iv = new Buffer("0000000000000000");
  let decodeKey = crypto
    .createHash(cryptConfig.algorithm)
    .update(cryptConfig.key, "utf-8")
    .digest();
  let cipher = crypto.createCipheriv(cryptConfig.password, decodeKey, iv);
  return cipher.update(text, "utf8", "hex") + cipher.final("hex");
};

const validateEmail = async (model, username) => {
  const effectedRow = await model.findOne({
    where: {
      username: username,
    },
  });
  if (effectedRow) return false;
  else return true;
};

export default registerUser;
