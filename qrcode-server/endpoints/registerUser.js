import schema from "../data/schema";
import crypto from "crypto";
import { cryptConfig } from "../serverconfig";

const registerUser = async (req, res) => {
  //const model = schema["user"];
  let data = req.body;
  try {
    // if (await validateEmail(model, data.username)) {
    //   data.userKey = setEncrypt(data.username);
    //   data.password = setEncrypt(data.password);
    //   let entity = await model.create(data);
    //   entity = {
    //     userKey: entity.userKey,
    //   };
    //   return res.status(200).json({ success: true, data: entity });
    // } else {
    //   return res
    //     .status(200)
    //     .json({ success: false, data: "Username Alrey Exists" });
    // }
    const value = setEncrypt(data.username);
    return res.status(200).json({ success: true, data: value });
  } catch (error) {
    return res.status(200).json({ success: false, data: error });
  }
};

const setEncrypt = (key) => {
  let cipher = crypto.createCipher(cryptConfig.algorithm, cryptConfig.password);
  let crypted = cipher.update(key, "utf8", "hex");
  crypted += cipher.final("hex");
  return crypted;
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
